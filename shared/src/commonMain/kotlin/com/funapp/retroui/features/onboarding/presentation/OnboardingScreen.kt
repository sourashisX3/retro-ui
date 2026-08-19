package com.funapp.retroui.features.onboarding.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieAnimatable
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import com.funapp.retroui.core.ui.animation.RetroEntranceStyle
import com.funapp.retroui.core.ui.animation.retroEntrance
import com.funapp.retroui.core.ui.components.controls.RetroButton
import com.funapp.retroui.core.ui.components.controls.RetroButtonVariant
import com.funapp.retroui.core.ui.components.feedback.RetroEmptyState
import com.funapp.retroui.core.ui.components.surfaces.RetroScreenStatic
import com.funapp.retroui.core.ui.theme.RetroTheme
import com.funapp.retroui.core.ui.token.RetroAnimation
import kotlin.math.absoluteValue
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import retroui.shared.generated.resources.Res
import retroui.shared.generated.resources.btn_already_player
import retroui.shared.generated.resources.btn_skip
import retroui.shared.generated.resources.btn_start_playing
import retroui.shared.generated.resources.common_next
import retroui.shared.generated.resources.onboarding_page_1_body
import retroui.shared.generated.resources.onboarding_page_1_title
import retroui.shared.generated.resources.onboarding_page_2_body
import retroui.shared.generated.resources.onboarding_page_2_title

private data class OnboardingPage(
    val animationPath: String,
    val title: String,
    val body: String,
)

/**
 * Onboarding: a 2-page arcade intro pager with looping Lottie animations
 * (compottie) per page. Pages fade/slide, a pixel dot indicator tracks
 * progress, SKIP/ALREADY PLAYER hop to login and START PLAYING (last page)
 * begins the auth flow.
 */
@Composable
fun OnboardingScreen(
    onStart: () -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = RetroTheme.spacing

    val pages = listOf(
        OnboardingPage(
            animationPath = "files/question_coin.json",
            title = stringResource(Res.string.onboarding_page_1_title),
            body = stringResource(Res.string.onboarding_page_1_body),
        ),
        OnboardingPage(
            animationPath = "files/pixel_duck.json",
            title = stringResource(Res.string.onboarding_page_2_title),
            body = stringResource(Res.string.onboarding_page_2_body),
        ),
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val isLastPage = pagerState.currentPage == pages.lastIndex
    val scope = rememberCoroutineScope()
    val skipLabel = stringResource(Res.string.btn_skip)
    val nextLabel = stringResource(Res.string.common_next)
    val startLabel = stringResource(Res.string.btn_start_playing)

    RetroScreenStatic(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = spacing.lg,
            end = spacing.lg,
            bottom = spacing.md,
        ),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Skip (top-right)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                RetroButton(
                    text = skipLabel,
                    variant = RetroButtonVariant.Outline,
                    small = true,
                    onClick = onLogin,
                )
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) { page ->
                val pageOffset = (pagerState.currentPage - page + pagerState.currentPageOffsetFraction)
                val offScreen = pageOffset.absoluteValue.coerceIn(0f, 1f)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(1f - offScreen * 0.35f),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OnboardingLottie(
                            animationPath = pages[page].animationPath,
                            modifier = Modifier
                                .size(160.dp)
                                .retroEntrance(style = RetroEntranceStyle.Coin),
                        )
                        RetroEmptyState(
                            title = pages[page].title,
                            subtitle = pages[page].body,
                            icon = null,
                        )
                    }
                }
            }

            OnboardingDots(
                count = pages.size,
                selected = pagerState.currentPage,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(spacing.lg))
            RetroButton(
                text = if (isLastPage) startLabel else nextLabel,
                onClick = {
                    if (isLastPage) {
                        onStart()
                    } else {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(spacing.sm))
            RetroButton(
                text = stringResource(Res.string.btn_already_player),
                variant = RetroButtonVariant.Outline,
                onClick = onLogin,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(spacing.md))
        }
    }
}

/** Looping Lottie animation loaded from the shared compose resources. */
@Composable
private fun OnboardingLottie(
    animationPath: String,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(Res.readBytes(animationPath).decodeToString())
    }
    val animatable = rememberLottieAnimatable()
    LaunchedEffect(composition) {
        animatable.animate(composition, iterations = Compottie.IterateForever)
    }
    Image(
        painter = rememberLottiePainter(composition, progress = animatable::value),
        contentDescription = null,
        modifier = modifier,
    )
}

/** Pixel dot indicator: the active dot stretches into a capsule. */
@Composable
private fun OnboardingDots(
    count: Int,
    selected: Int,
    modifier: Modifier = Modifier,
) {
    val colors = RetroTheme.colors
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        repeat(count) { index ->
            val active = index == selected
            val scaleX by animateFloatAsState(
                targetValue = if (active) 2.25f else 1f,
                animationSpec = if (active) RetroAnimation.pop else RetroAnimation.press,
                label = "onboardingDotScaleX",
            )
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(width = 8.dp, height = 8.dp)
                    .graphicsLayer { this.scaleX = scaleX }
                    .clip(RetroTheme.shapeTokens.chip)
                    .background(if (active) colors.primary else colors.surfaceMuted)
                    .border(RetroTheme.borders.thin, colors.outlineStrong, RetroTheme.shapeTokens.chip),
            )
        }
    }
}
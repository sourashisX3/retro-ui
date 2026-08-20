package com.funapp.retroui.core.ui.token

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funapp.retroui.core.ui.theme.DarkRetroColors
import com.funapp.retroui.core.ui.theme.LightRetroColors
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Design-token sanity tests: the values below are the contract the whole UI
 * depends on. Changing a pin (or failing a contrast gate) is a deliberate,
 * reviewed decision — not an accident.
 */
class TokenSanityTest {

    // ---------------------------------------------------------------- palette

    @Test
    fun primitivePaletteIsPinned() {
        assertEquals(Color(0xFFF3E9D2), RetroPrimitiveColors.Paper)
        assertEquals(Color(0xFF171717), RetroPrimitiveColors.Ink)
        assertEquals(Color(0xFF45D66F), RetroPrimitiveColors.Green)
        assertEquals(Color(0xFF0E1220), RetroPrimitiveColors.Night)
        assertEquals(Color(0xFF54639A), RetroPrimitiveColors.NightOutline)
        assertEquals(Color(0xFF3A4670), RetroPrimitiveColors.NightShadow)
        assertEquals(Color(0xFFF4F6FB), RetroPrimitiveColors.NightInk)
    }

    // ----------------------------------------------------------- spacing grid

    @Test
    fun spacingScaleStaysOn4dpGrid() {
        val s = DefaultRetroSpacing
        assertEquals(4f, s.xs.value, "xs must be 4dp")
        assertEquals(8f, s.sm.value, "sm must be 8dp")
        assertEquals(12f, s.md.value, "md must be 12dp")
        assertEquals(16f, s.lg.value, "lg must be 16dp")
        // documented exception: hairline gaps
        assertEquals(2f, s.xxs.value, "xxs is the documented 2dp exception")
        listOf(s.xs, s.sm, s.md, s.lg).forEach { step ->
            assertEquals(0f, step.value % 4f, "spacing step must stay on the 4dp grid")
        }
    }

    // -------------------------------------------------------------- shapes

    @Test
    fun shapeTokensStaySquared() {
        assertEquals(RoundedCornerShape(2.dp), DefaultRetroShapeTokens.button)
        assertEquals(RoundedCornerShape(100.dp), DefaultRetroShapeTokens.buttonPill)
        assertEquals(RoundedCornerShape(4.dp), DefaultRetroShapeTokens.card)
        assertEquals(RoundedCornerShape(6.dp), DefaultRetroShapeTokens.dialog)
    }

    @Test
    fun motionDurationsArePinned() {
        assertEquals(120, RetroMotion.FastMs)
        assertEquals(240, RetroMotion.NormalMs)
        assertEquals(400, RetroMotion.SlowMs)
        assertEquals(600, RetroMotion.ExpressiveMs)
    }

    // --------------------------------------------------------------- contrast

    @Test
    fun lightThemeTextMeetsWcag() {
        val c = LightRetroColors
        // Body text on solid fills: AA (>= 4.5:1)
        assertTrue(contrast(c.onPrimary, c.primary) >= 4.5, "onPrimary on primary")
        assertTrue(contrast(c.onSecondary, c.secondary) >= 4.5, "onSecondary on secondary")
        assertTrue(contrast(c.onError, c.error) >= 4.5, "onError on error")
        // Accent fills carry bold heading text: AA-large (>= 3:1)
        assertTrue(contrast(c.onAccent, c.accent) >= 3.0, "onAccent on accent")
        // Page text: AAA (>= 7:1)
        assertTrue(contrast(c.onBackground, c.background) >= 7.0, "onBackground on background")
        assertTrue(contrast(c.textPrimary, c.background) >= 7.0, "textPrimary on background")
        assertTrue(contrast(c.textSecondary, c.background) >= 4.5, "textSecondary on background")
        assertTrue(contrast(c.textMuted, c.background) >= 3.0, "textMuted on background")
    }

    @Test
    fun darkThemeTextMeetsWcag() {
        val c = DarkRetroColors
        assertTrue(contrast(c.onPrimary, c.primary) >= 4.5, "onPrimary on primary")
        assertTrue(contrast(c.onSecondary, c.secondary) >= 4.5, "onSecondary on secondary")
        assertTrue(contrast(c.onAccent, c.accent) >= 3.0, "onAccent on accent")
        assertTrue(contrast(c.onError, c.error) >= 4.5, "onError on error")
        assertTrue(contrast(c.onBackground, c.background) >= 7.0, "onBackground on background")
        assertTrue(contrast(c.textSecondary, c.background) >= 4.5, "textSecondary on background")
        assertTrue(contrast(c.textMuted, c.background) >= 3.0, "textMuted on background")
        assertTrue(contrast(c.textPrimary, c.surface) >= 4.5, "textPrimary on surface")
    }

    // ------------------------------------------------------------- helpers

    private fun contrast(a: Color, b: Color): Double {
        val la = relativeLuminance(a)
        val lb = relativeLuminance(b)
        val hi = max(la, lb)
        val lo = min(la, lb)
        return (hi + 0.05) / (lo + 0.05)
    }

    private fun relativeLuminance(c: Color): Double {
        fun channel(v: Float): Double {
            val s = v / 255.0
            return if (s <= 0.03928) s / 12.92 else ((s + 0.055) / 1.055).pow(2.4)
        }
        return 0.2126 * channel(c.red * 255f) +
            0.7152 * channel(c.green * 255f) +
            0.0722 * channel(c.blue * 255f)
    }
}
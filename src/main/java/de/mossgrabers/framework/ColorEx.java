// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework;

/**
 * Some helper constans for Color.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class ColorEx
{
    /** Color black. */
    public static final ColorEx BLACK      = ColorEx.fromRGB (0, 0, 0);
    /** Color white. */
    public static final ColorEx WHITE      = ColorEx.fromRGB (255, 255, 255);
    /** Color light gray. */
    public static final ColorEx LIGHT_GRAY = ColorEx.fromRGB (182, 182, 182);
    /** Color gray. */
    public static final ColorEx GRAY       = ColorEx.fromRGB (128, 128, 128);
    /** Color dark gray. */
    public static final ColorEx DARK_GRAY  = ColorEx.fromRGB (89, 89, 89);
    /** Color red. */
    public static final ColorEx RED        = ColorEx.fromRGB (255, 0, 0);
    /** Color green. */
    public static final ColorEx GREEN      = ColorEx.fromRGB (0, 255, 0);
    /** Color yellow. */
    public static final ColorEx YELLOW     = ColorEx.fromRGB (255, 255, 0);

    private static final double FACTOR     = 0.7;

    private final double        red;
    private final double        green;
    private final double        blue;


    /**
     * Constructor.
     *
     * @param red The red component
     * @param green The green component
     * @param blue The blue component
     */
    public ColorEx (final double red, final double green, final double blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


    /**
     * Create a new color instance from 255 ints.
     *
     * @param red The red component
     * @param green The green component
     * @param blue The blue component
     * @return The new color
     */
    public static ColorEx fromRGB (final int red, final int green, final int blue)
    {
        return new ColorEx (red / 255.0, green / 255.0, blue / 255.0);
    }


    /**
     * Calculates a brighter version of the given color.
     *
     * @param c A color
     * @return The brighter version
     */
    public static ColorEx brighter (final ColorEx c)
    {
        double r = c.getRed ();
        double g = c.getGreen ();
        double b = c.getBlue ();

        // From 2D group:
        // 1. black.brighter() should return grey
        // 2. applying brighter to blue will always return blue, brighter
        // 3. non pure color (non zero rgb) will eventually return white

        final double i = 1.0 / (1.0 - FACTOR) / 255.0;

        if (r == 0 && g == 0 && b == 0)
            return new ColorEx (i, i, i);

        if (r > 0 && r < i)
            r = i;

        if (g > 0 && g < i)
            g = i;

        if (b > 0 && b < i)
            b = i;

        return new ColorEx (Math.min (r / FACTOR, 1.0), Math.min (g / FACTOR, 1.0), Math.min (b / FACTOR, 1.0));
    }


    /**
     * Calculates a darker version of the given color.
     *
     * @param c A color
     * @return The brighter version
     */
    public static ColorEx darker (final ColorEx c)
    {
        return new ColorEx (Math.max (c.getRed () * FACTOR, 0), Math.max (c.getGreen () * FACTOR, 0), Math.max (c.getBlue () * FACTOR, 0));
    }


    /**
     * Get the red component.
     *
     * @return The red component
     */
    public double getRed ()
    {
        return this.red;
    }


    /**
     * Get the green component.
     *
     * @return The green component
     */
    public double getGreen ()
    {
        return this.green;
    }


    /**
     * Get the blue component.
     *
     * @return The blue component
     */
    public double getBlue ()
    {
        return this.blue;
    }
}

package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class DoBrightness.
 */
public class DoBrightness implements Filter {

	private static final Integer MAX = 255;

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The value. */
	private int value;

	/**
	 * Instantiates a new do brightness.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param value
	 *            the value
	 */
	public DoBrightness(Bitmap bitmapIn, int value) {
		this.bitmapIn = bitmapIn;
		this.value = value;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mDoBrightness
	 *            the m do brightness
	 * @return the bitmap
	 */
	@Override
	public Bitmap executeFilter() {

		long time = System.currentTimeMillis();
		int width = this.getBitmapIn().getWidth();
		int height = this.getBitmapIn().getHeight();
		Bitmap bitmapOut = null;
		int red, green, blue, pixel;
		int[] pixels = new int[width * height];
		this.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {
			pixel = pixels[i];
			red = Color.red(pixel);
			green = Color.green(pixel);
			blue = Color.blue(pixel);

			red += this.getValue();
			if (red > MAX)
				red = MAX;
			else if (red < 0)
				red = 0;

			green += this.getValue();
			if (green > MAX)
				green = MAX;
			else if (green < 0)
				green = 0;

			blue += this.getValue();
			if (blue > MAX)
				blue = MAX;
			else if (blue < 0)
				blue = 0;

			pixels[i] = Color.argb(Color.alpha(pixel), red, green, blue);
		}
		bitmapOut = Bitmap.createBitmap(width, height, this.getBitmapIn().getConfig());
		bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height);
		pixels = null;
		time = System.currentTimeMillis() - time;
		System.out.println("Finished @ " + time + "ms");

		return bitmapOut;
	}

	/**
	 * Gets the bitmap in.
	 * 
	 * @return the bitmap in
	 */
	public Bitmap getBitmapIn() {
		return bitmapIn;
	}

	/**
	 * Sets the bitmap in.
	 * 
	 * @param bitmapIn
	 *            the new bitmap in
	 */
	public void setBitmapIn(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(int value) {
		this.value = value;
	}
}

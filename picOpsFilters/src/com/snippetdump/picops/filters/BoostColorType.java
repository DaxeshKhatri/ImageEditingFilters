package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class BoostColorType.
 */
public class BoostColorType implements Filter {

	private static final int MAX = 255;

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The type. */
	private int type;

	/** The percent. */
	private float percent;

	/** The Constant RED. */
	private static final int RED = 1;

	/** The Constant GREEN. */
	private static final int GREEN = 2;

	/** The Constant BLUE. */
	private static final int BLUE = 3;

	/**
	 * Instantiates a new boost color type.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param type
	 *            the type
	 * @param percent
	 *            the percent
	 */
	public BoostColorType(Bitmap bitmapIn, int type, float percent) {
		this.bitmapIn = bitmapIn;
		this.type = type;
		this.percent = percent;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mBoostColorType
	 *            the m boost color type
	 * @return the bitmap
	 */
	@Override
	public Bitmap executeFilter() {

		long time = System.currentTimeMillis();
		int width = this.getBitmapIn().getWidth();
		int height = this.getBitmapIn().getHeight();
		int alpha, red, green, blue;
		int[] pixels = new int[width * height];
		this.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {

			alpha = Color.alpha(pixels[i]);
			red = Color.red(pixels[i]);
			green = Color.green(pixels[i]);
			blue = Color.blue(pixels[i]);

			if (this.getType() == RED) {
				red = (int) (red * (1 + this.getPercent()));
				if (red > MAX)
					red = MAX;
			}

			if (this.getType() == GREEN) {
				green = (int) (green * (1 + this.getPercent()));
				if (green > MAX)
					green = MAX;
			}

			if (this.getType() == BLUE) {
				blue = (int) (blue * (1 + this.getPercent()));
				if (blue > MAX)
					blue = MAX;
			}
			pixels[i] = Color.argb(alpha, red, green, blue);
		}
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, this.getBitmapIn()
				.getConfig());
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets the percent.
	 * 
	 * @return the percent
	 */
	public float getPercent() {
		return percent;
	}

	/**
	 * Sets the percent.
	 * 
	 * @param percent
	 *            the new percent
	 */
	public void setPercent(float percent) {
		this.percent = percent;
	}
}

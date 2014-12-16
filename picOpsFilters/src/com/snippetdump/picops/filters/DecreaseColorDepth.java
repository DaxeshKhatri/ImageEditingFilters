package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class DecreaseColorDepth.
 */
public class DecreaseColorDepth implements Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The bit offset. */
	private int bitOffset;

	/**
	 * Instantiates a new decrease color depth.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param bitOffset
	 *            the bit offset
	 */
	public DecreaseColorDepth(Bitmap bitmapIn, int bitOffset) {
		this.bitmapIn = bitmapIn;
		this.bitOffset = bitOffset;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mDecreaseColorDepth
	 *            the m decrease color depth
	 * @return the bitmap
	 */
	@Override
	public Bitmap executeFilter() {

		if (this.getBitOffset() == 0) {
			this.setBitOffset(32);
		} else if (this.getBitOffset() == 1) {
			this.setBitOffset(64);
		} else if (this.getBitOffset() == 2) {
			this.setBitOffset(128);
		}
		long time = System.currentTimeMillis();
		int width = this.getBitmapIn().getWidth();
		int height = this.getBitmapIn().getHeight();
		int[] pixels = new int[width * height];
		int red, green, blue;
		this.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {

			red = Color.red(pixels[i]);
			green = Color.green(pixels[i]);
			blue = Color.blue(pixels[i]);

			red = ((red + (this.getBitOffset() / 2))
					- ((red + (this.getBitOffset() / 2)) % this
							.getBitOffset()) - 1);
			if (red < 0)
				red = 0;

			green = ((green + (this.getBitOffset() / 2))
					- ((green + (this.getBitOffset() / 2)) % this
							.getBitOffset()) - 1);
			if (green < 0)
				green = 0;

			blue = ((blue + (this.getBitOffset() / 2))
					- ((blue + (this.getBitOffset() / 2)) % this
							.getBitOffset()) - 1);
			if (blue < 0)
				blue = 0;

			pixels[i] = Color.argb(Color.alpha(pixels[i]), red, green, blue);
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
	 * Gets the bit offset.
	 * 
	 * @return the bit offset
	 */
	public int getBitOffset() {
		return bitOffset;
	}

	/**
	 * Sets the bit offset.
	 * 
	 * @param bitOffset
	 *            the new bit offset
	 */
	public void setBitOffset(int bitOffset) {
		this.bitOffset = bitOffset;
	}
}

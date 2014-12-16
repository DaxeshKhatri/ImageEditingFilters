package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * The Class RotateImage.
 */
public class RotateImage implements Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The degree. */
	private float degree;

	/**
	 * Instantiates a new rotate image.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param degree
	 *            the degree
	 */
	public RotateImage(Bitmap bitmapIn, float degree) {
		this.bitmapIn = bitmapIn;
		this.degree = degree;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mRotateImage
	 *            the m rotate image
	 * @return the bitmap
	 */
	@Override
	public Bitmap executeFilter() {

		long time = System.currentTimeMillis();
		Matrix matrix = new Matrix();
		matrix.postRotate(this.getDegree());
		time = System.currentTimeMillis() - time;
		System.out.println("Finished @ " + time + "ms");

		return Bitmap.createBitmap(this.getBitmapIn(), 0, 0, this.getBitmapIn()
				.getWidth(), this.getBitmapIn().getHeight(), matrix, true);
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
	 * Gets the degree.
	 * 
	 * @return the degree
	 */
	public float getDegree() {
		return degree;
	}

	/**
	 * Sets the degree.
	 * 
	 * @param degree
	 *            the new degree
	 */
	public void setDegree(float degree) {
		this.degree = degree;
	}
}

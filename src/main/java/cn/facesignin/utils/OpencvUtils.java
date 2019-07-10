package cn.facesignin.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import cn.facesignin.constant.OpencvConfig;

public class OpencvUtils {
	
	private static OpencvUtils opencvUtils;
	
	static {
		opencvUtils = new OpencvUtils();
	}
	
	private OpencvUtils() {
	}
	
	public static OpencvUtils getInstance() {
		return opencvUtils;
	}
	
	/**
	 * 获得人脸矩形框
	 * @param abName 绝对路径
	 */
	public List<Rect> getFaceRects(String absolutePath){
		// 加载分类器
		CascadeClassifier faceDetector = new CascadeClassifier(OpencvConfig.CLASSIFIER);
		if (faceDetector.empty()) {
			return null;
		}
		// 创建Mat
		Mat image = Imgcodecs.imread(absolutePath);
		// 检测人脸，检测结果存在faceDetections中
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		return Arrays.asList(faceDetections.toArray());
	}
                                                                                                                                                                                                                                                                                                                                    	
	public File imageCut(String imagePath, String outFilePath, Rect rect) {
		Mat image = Imgcodecs.imread(imagePath);
		Mat sub = image.submat(rect); // Mat sub = new Mat(image, rect);
		Mat mat = new Mat();
		Size size = new Size(rect.width, rect.height);
		Imgproc.resize(sub, mat, size);// 将人脸进行截图并保存
		Imgcodecs.imwrite(outFilePath, mat);
		return new File(outFilePath);
	}

	public File imageMark(String imagePath, String outFilePath, Rect rect) {
		Mat image = Imgcodecs.imread(imagePath);	// 原始图片
		Imgproc.rectangle(image, new Point(rect.x, rect.y), // 左上点
				new Point(rect.x + rect.width, rect.y + rect.height), // 右下点
				new Scalar(0, 255, 0), 2); // 框的颜色和粗细
		// 把mat写入图片
		Imgcodecs.imwrite(outFilePath, image);
		return new File(outFilePath);
	}

	public static void enlargeRects(String absolutePath, List<Rect> rects, int size) {
		Iterator<Rect> iterator = rects.iterator();
		Mat image = Imgcodecs.imread(absolutePath);
		int width = image.width();
		int height = image.height();
		while(iterator.hasNext()) {
			Rect rect = iterator.next();
			rect.x = (rect.x - size) < 0 ? 0 : rect.x - size;
			rect.y = (rect.y - size) < 0 ? 0 : rect.y - size;
			rect.width = (rect.width + size * 2) > width ? width : rect.width + size * 2;
			rect.height = (rect.height + size * 2) > height ? height : rect.height + size * 2;
		}
	}
	public static void main(String[] args) {
		OpencvUtils opencvUtils = new OpencvUtils();
		String absolutePath = "D:\\aaa.jpg";
		List<Rect> rects = opencvUtils.getFaceRects(absolutePath);
		Iterator<Rect> iterator = rects.iterator();
		OpencvUtils.enlargeRects(absolutePath, rects, 20);
		
		while(iterator.hasNext()) {
			Rect rect = iterator.next();
			File imageMark = opencvUtils.imageMark(absolutePath, "D:\\tempopencv\\" + UUID.randomUUID() + ".jpg", rect);
		}
	}
}
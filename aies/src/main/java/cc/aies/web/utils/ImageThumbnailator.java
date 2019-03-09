package cc.aies.web.utils;

/**
 * @Auther: qiuzp
 * @Date: 18-9-3 下午7:19
 * @Description:对图像的切割等操作
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public  class ImageThumbnailator {



    /**
     * 指定大小进行缩放
     *
     * @throws IOException
     */
    private void zoomBySize() throws IOException {
        /*
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of("").size(200, 300).toFile(
                "");
        Thumbnails.of("").size(2560, 2048).toFile(
                "");
    }

    /**
     * 按照比例进行缩放
     *
     * @throws IOException
     */
    private void zoomByScale() throws IOException {
        /**
         * scale(比例)
         */
        Thumbnails.of("").scale(0.25f)
                .toFile("");
        Thumbnails.of("").scale(1.10f).toFile(
                "");
    }

    /**
     * 不按照比例，指定大小进行缩放
     *
     * @throws IOException
     */
    private void zoom() throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         */
        Thumbnails.of("").size(120, 120).keepAspectRatio(false)
                .toFile("");
    }

    /**
     * 旋转
     *
     * @throws IOException
     */
    private void spin() throws IOException {
        /**
         * rotate(角度),正数：顺时针 负数：逆时针
         */
        Thumbnails.of("").size(1280, 1024).rotate(90).toFile(
                "");
        Thumbnails.of("").size(1280, 1024).rotate(-90).toFile(
                "");
    }

    /**
     * 水印
     *
     * @throws IOException
     */
    private void waterMark() throws IOException {
        /**
         * watermark(位置，水印图，透明度)
         */
        Thumbnails.of("").size(1280, 1024).watermark(
                Positions.BOTTOM_RIGHT,
                ImageIO.read(new File("")), 0.5f)
                .outputQuality(0.8f).toFile(
                "");
        Thumbnails.of("").size(1280, 1024).watermark(
                Positions.CENTER,
                ImageIO.read(new File("")), 0.5f)
                .outputQuality(0.8f).toFile("");
    }

    /**
     * 裁剪
     *
     * @throws IOException
     * 顺时针依次为p1,p2,p3,p4
     */
    public static void cut(int x1,int y1,int x2,int y2,String inputPath,String outputPath) throws IOException {

        /*Thumbnails.of("/IMG_0001.jpg").sourceRegion(Positions.CENTER, 400,
                400).size(200, 200).keepAspectRatio(false).toFile(
                "/1.jpg");

        Thumbnails.of("/IMG_0001.jpg").sourceRegion(Positions.BOTTOM_RIGHT,
                400, 400).size(200, 200).keepAspectRatio(false).toFile(
                "/2.jpg");*/
        /**
         * 指定坐标
         */
        Thumbnails.of(inputPath).sourceRegion(x1, y1, x2-x1, y2-y1).size(
                x2-x1, y2-y1).keepAspectRatio(false).toFile(
                outputPath);
    }

    /**
     * 转化图像格式
     *
     * @throws IOException
     */
    private void test7() throws IOException {
        /**
         * outputFormat(图像格式)
         */
        Thumbnails.of("").size(1280, 1024).outputFormat("png")
                .toFile("");
        Thumbnails.of("").size(1280, 1024).outputFormat("gif")
                .toFile("");
    }

    /**
     * 输出到OutputStream
     *
     * @throws IOException
     */
    private void test8() throws IOException {
        /**
         * toOutputStream(流对象)
         */
        OutputStream os = new FileOutputStream(
                "");
        Thumbnails.of("").size(1280, 1024).toOutputStream(os);
    }

    /**
     * 输出到BufferedImage
     *
     * @throws IOException
     */
    private void test9() throws IOException {
        /**
         * asBufferedImage() 返回BufferedImage
         */
        BufferedImage thumbnail = Thumbnails.of("").size(1280,
                1024).asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File(
                ""));
    }
}
//package com.image;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class AttachImage {
//
//    //http://nbdm.nbmz.gov.cn/AlatuMonth/map/nbjxdmt/Layers/_alllayers/L05/R0000002e/C0000003f.png
//    static String url = "http://nbdm.nbmz.gov.cn/AlatuMonth/map/nbjxdmt/Layers/_alllayers/L05/{R}/{C}.png";
//
//    public static void main(String[] args) {
////        String dst_pic = "/Users/handty/Desktop/test.png";
////        String[] pics = new String[]{"/Users/handty/Desktop/C00000000.png","/Users/handty/Desktop/C00000001.png"};
////        String type = "png";
////        try {
////            attachWidth(pics,dst_pic);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        System.out.printf(Integer.toHexString(17));
//
//        try {
//            //downLoadImg();
//            //todo
//            //attachWidth(46,63);
//
//            attachHeight(46);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void attachHeight(int maxR){
//        String base_directory = "/Users/handty/Desktop/r/";
//        //for(int i=0;i<=maxR;i++){
//            String[] fileNames = new String[maxR+1];
//            for(int r=0;r<=maxR;r++){
//                fileNames[r] = base_directory+r+".png";
//            }
//            merge(fileNames,"png",base_directory+"total.png");
//       // }
//    }
//
//    public static void attachWidth(int maxR,int maxC)throws Exception{
//        String base_directory = "/Users/handty/Desktop/r/";
//        //todo
//        for(int i=0;i<=maxR;i++){
//            String R = Integer.toHexString(i);
//            if(R.length() <=1){
//                R = "R0000000"+R;
//            }else{
//                R = "R000000"+R;
//            }
//            String directory = base_directory+R+"/";
//            String[] fileNames = new String[maxC+1];
//            for(int c=0;c<=maxC;c++){
//                fileNames[c] = directory+c+".png";
//            }
//            System.out.println(directory);
//            String dst = "/Users/handty/Desktop/r/"+i+".png";
//            //attachWidth(fileNames,dst);
//            mergeWidth(fileNames,"png",dst);
//
//        }
//    }
//
//
//    /**
//     * C 3f
//     * R 2e
//     */
//    public static void downLoadImg() throws Exception{
//        String base_directory = "/Users/handty/Desktop/r/";
//        for(int r =0;r<=46;r++){
//           // String R =
//            String R = Integer.toHexString(r);
//            if(R.length() <=1){
//                R = "R0000000"+R;
//            }else{
//                R = "R000000"+R;
//            }
//            String directory = base_directory+R+"/";
//            File file = new File(directory);
//            if(!file.exists()){
//                file.mkdirs();
//            }
//            for(int c=0;c<=63;c++){
//                String fileName = c+".png";
//                String realName = directory+fileName;
//                String C = Integer.toHexString(c);
//                if(C.length() <= 1){
//                    C = "C0000000"+C;
//                }else{
//                    C = "C000000"+C;
//                }
//                String realUrl = url.replace("{R}",R).replace("{C}",C);
//                downloadImage(realUrl,directory+fileName);
//
//            }
//        }
//    }
//
//    private static void downloadImage(String url,String filePath)throws Exception{
//        download(url,filePath);
//
//    }
//    /**
//     * Java拼接多张图片
//     *
//     * @param pics
//     * @param type
//     * @param dst_pic
//     * @return
//     */
//    public static boolean merge(String[] pics, String type, String dst_pic) {
//
//        int len = pics.length;
//        if (len < 1) {
//            System.out.println("pics len < 1");
//            return false;
//        }
//        File[] src = new File[len];
//        BufferedImage[] images = new BufferedImage[len];
//        int[][] ImageArrays = new int[len][];
//        for (int i = 0; i < len; i++) {
//            try {
//                src[i] = new File(pics[i]);
//                images[i] = ImageIO.read(src[i]);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//            int width = images[i].getWidth();
//            int height = images[i].getHeight();
//            ImageArrays[i] = new int[width * height];// 从图片中读取RGB
//            ImageArrays[i] = images[i].getRGB(0, 0, width, height,
//                    ImageArrays[i], 0, width);
//        }
//
//        int dst_height = 0;
//        int dst_width = images[0].getWidth();
//        for (int i = 0; i < images.length; i++) {
//            dst_width = dst_width > images[i].getWidth() ? dst_width
//                    : images[i].getWidth();
//
//            dst_height += images[i].getHeight();
//        }
//        System.out.println(dst_width);
//        System.out.println(dst_height);
//        if (dst_height < 1) {
//            System.out.println("dst_height < 1");
//            return false;
//        }
//
//        // 生成新图片
//        try {
//            // dst_width = images[0].getWidth();
//            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,
//                    BufferedImage.TYPE_INT_RGB);
//            int height_i = 0;
//            for (int i = 0; i < images.length; i++) {
//                ImageNew.setRGB(0, height_i, dst_width, images[i].getHeight(),
//                        ImageArrays[i], 0, dst_width);
//                height_i += images[i].getHeight();
//            }
//
//            File outFile = new File(dst_pic);
//            ImageIO.write(ImageNew, type, outFile);// 写图片
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean mergeWidth(String[] pics, String type, String dst_pic) {
//
//        int len = pics.length;
//        if (len < 1) {
//            System.out.println("pics len < 1");
//            return false;
//        }
//        File[] src = new File[len];
//        BufferedImage[] images = new BufferedImage[len];
//        int[][] ImageArrays = new int[len][];
//        for (int i = 0; i < len; i++) {
//            try {
//                src[i] = new File(pics[i]);
//                images[i] = ImageIO.read(src[i]);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//            int width = images[i].getWidth();
//            int height = images[i].getHeight();
//            ImageArrays[i] = new int[width * height];// 从图片中读取RGB
//            ImageArrays[i] = images[i].getRGB(0, 0, width, height,
//                    ImageArrays[i], 0, width);
//        }
//
//        int dst_height = images[0].getHeight();
//        int dst_width = 0;
//        for (int i = 0; i < images.length; i++) {
//            dst_height = dst_height > images[i].getHeight() ? dst_height
//                    : images[i].getHeight();
//
//            dst_width += images[i].getWidth();
//        }
//        System.out.println(dst_width);
//        System.out.println(dst_height);
//        if (dst_width < 1) {
//            System.out.println("dst_width < 1");
//            return false;
//        }
//
//        // 生成新图片
//        try {
//            // dst_width = images[0].getWidth();
//            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,
//                    BufferedImage.TYPE_INT_RGB);
//            int width_i = 0;
//            for (int i = 0; i < images.length; i++) {
//                ImageNew.setRGB(width_i, 0, images[i].getWidth(),  dst_height ,
//                        ImageArrays[i], 0, dst_height);
//                width_i += images[i].getWidth();
//            }
//
//            File outFile = new File(dst_pic);
//            ImageIO.write(ImageNew, type, outFile);// 写图片
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    public static void attachWidth(String[] pics, String dst_pic) throws Exception {
//        //创建四个文件对象（注：这里四张图片的宽度都是相同的，因此下文定义BufferedImage宽度就取第一只的宽度就行了）
////        File _file1 = new File("1.jpg");
////        File _file2 = new File("2.jpg");
////        File _file3 = new File("3.jpg");
////        File _file4 = new File("4.jpg");
////
////        Image src1 = javax.imageio.ImageIO.read(_file1);
////        Image src2 = javax.imageio.ImageIO.read(_file2);
////        Image src3 = javax.imageio.ImageIO.read(_file3);
////        Image src4 = javax.imageio.ImageIO.read(_file4);
//
//        //获取图片的宽度
////        int width = src1.getWidth(null);
////        //获取各个图像的高度
////        int height1 = src1.getHeight(null);
////        int height2 = src2.getHeight(null);
////        int height3 = src3.getHeight(null);
////        int height4 = src4.getHeight(null);
//        int tWidth = 0;
//        int tHeight = 0;
//        Image[] images = new Image[pics.length];
//        for(int i=0;i<pics.length;i++){
//            System.out.println(pics[i]);
//            Image src = ImageIO.read(new File(pics[i]));
//            images[i]= src;
//            int width = src.getWidth(null);
//            int height = src.getHeight(null);
//            tHeight = height;
//            //g.drawImage(src1, totalWidth, 0, width+totalWidth, height, null);
//            tWidth += width;
//        }
//        //构造一个类型为预定义图像类型之一的 BufferedImage。 宽度为第一只的宽度，高度为各个图片高度之和
//        BufferedImage tag = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_INT_RGB);
//        //创建输出流
//        FileOutputStream out = new FileOutputStream(dst_pic);
//        //绘制合成图像
//        Graphics g = tag.createGraphics();
//
//        int totalWidth = 0;
//        for(int i=0;i<images.length;i++){
//            Image src = images[i];
//            int width = src.getWidth(null);
//            int height = src.getHeight(null);
//            g.drawImage(src, totalWidth, 0, width+totalWidth, height, null);
//            totalWidth += width;
//        }
//
////        g.drawImage(src1, 0, 0, width, height1, null);
////        g.drawImage(src2, 0, height1, width , height2, null);
////        g.drawImage(src3, 0, height1 + height2, width, height3, null);
////        g.drawImage(src4, 0, height1 + height2 + height3, width, height4, null);
//        // 释放此图形的上下文以及它使用的所有系统资源。
//        g.dispose();
//        //将绘制的图像生成至输出流
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        encoder.encode(tag);
//        //关闭输出流
//        out.close();
//       // System.out.println("藏宝图出来了");
//    }
//
//
//    public static void attachHeight(String[] pics, String dst_pic) throws Exception {
//        //创建四个文件对象（注：这里四张图片的宽度都是相同的，因此下文定义BufferedImage宽度就取第一只的宽度就行了）
////        File _file1 = new File("1.jpg");
////        File _file2 = new File("2.jpg");
////        File _file3 = new File("3.jpg");
////        File _file4 = new File("4.jpg");
////
////        Image src1 = javax.imageio.ImageIO.read(_file1);
////        Image src2 = javax.imageio.ImageIO.read(_file2);
////        Image src3 = javax.imageio.ImageIO.read(_file3);
////        Image src4 = javax.imageio.ImageIO.read(_file4);
//
//        //获取图片的宽度
////        int width = src1.getWidth(null);
////        //获取各个图像的高度
////        int height1 = src1.getHeight(null);
////        int height2 = src2.getHeight(null);
////        int height3 = src3.getHeight(null);
////        int height4 = src4.getHeight(null);
//        int tWidth = 0;
//        int tHeight = 0;
//        for(int i=0;i<pics.length;i++){
//            Image src = ImageIO.read(new File(pics[i]));
//            int width = src.getWidth(null);
//            int height = src.getHeight(null);
//            tHeight += height;
//            //g.drawImage(src1, totalWidth, 0, width+totalWidth, height, null);
//            tWidth = width;
//        }
//        //构造一个类型为预定义图像类型之一的 BufferedImage。 宽度为第一只的宽度，高度为各个图片高度之和
//        BufferedImage tag = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_INT_RGB);
//        //创建输出流
//        FileOutputStream out = new FileOutputStream(dst_pic);
//        //绘制合成图像
//        Graphics g = tag.createGraphics();
//
//        int totalHeight = 0;
//        for(int i=0;i<pics.length;i++){
//            Image src = ImageIO.read(new File(pics[i]));
//            int width = src.getWidth(null);
//            int height = src.getHeight(null);
//            g.drawImage(src, 0, totalHeight, width, height+totalHeight, null);
//            totalHeight += height;
//        }
//
////        g.drawImage(src1, 0, 0, width, height1, null);
////        g.drawImage(src2, 0, height1, width , height2, null);
////        g.drawImage(src3, 0, height1 + height2, width, height3, null);
////        g.drawImage(src4, 0, height1 + height2 + height3, width, height4, null);
//        // 释放此图形的上下文以及它使用的所有系统资源。
//        g.dispose();
//        //将绘制的图像生成至输出流
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        encoder.encode(tag);
//        //关闭输出流
//        out.close();
//        System.out.println("藏宝图出来了");
//    }
//
//
//    public static void download(String urlPath,String filePth) throws Exception {
//        //new一个URL对象
//        URL url = new URL(urlPath);
//        System.out.println(urlPath);
//        //打开链接
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        //设置请求方式为"GET"
//        conn.setRequestMethod("GET");
//        //超时响应时间为5秒
//        conn.setConnectTimeout(5 * 1000);
//        //通过输入流获取图片数据
//        InputStream inStream = conn.getInputStream();
//        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
//        byte[] data = readInputStream(inStream);
//        //new一个文件对象用来保存图片，默认保存当前工程根目录
//        File imageFile = new File(filePth);
//        //创建输出流
//        FileOutputStream outStream = new FileOutputStream(imageFile);
//        //写入数据
//        outStream.write(data);
//        //关闭输出流
//        outStream.close();
//    }
//    public static byte[] readInputStream(InputStream inStream) throws Exception{
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        //创建一个Buffer字符串
//        byte[] buffer = new byte[1024];
//        //每次读取的字符串长度，如果为-1，代表全部读取完毕
//        int len = 0;
//        //使用一个输入流从buffer里把数据读取出来
//        while( (len=inStream.read(buffer)) != -1 ){
//            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
//            outStream.write(buffer, 0, len);
//        }
//        //关闭输入流
//        inStream.close();
//        //把outStream里的数据写入内存
//        return outStream.toByteArray();
//    }
//}
//
//

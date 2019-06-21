@Controller
@RequestMapping("/demo")
public class DownloadFromInterface{

      private final String url = "";
    
      @RequestMapping("download")
      public void downloadFile(HttpServletRequest request, HttpServletResponse response){
          String userAgent = request.getHeader("USER-AGENT");
          String fileName = "";
          try {
              fileName = URLDecoder.decode(request.getParameter("fileName"),"utf-8");
              if (StringUtils.contains(userAgent, "MSIE")
                      || StringUtils.contains(userAgent, "Trident")
                      || StringUtils.contains(userAgent, "Edge")) {//IE浏览器
                  fileName = URLEncoder.encode(fileName, "UTF-8");
              } else if (StringUtils.contains(userAgent, "Mozilla")) {//google,火狐浏览器
                  fileName = new String(fileName.getBytes(), "ISO8859-1");
              } else {
                  fileName = URLEncoder.encode(fileName, "UTF-8");//其他浏览器
              }
          } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
          }
          String fileId = request.getParameter("fileId");//接口里要传的文件的id
          String fileType = request.getParameter("fileType");//接口里要传的文件的类型
          String fileExt = request.getParameter("fileExt");//这是文件的后缀名
          response.setCharacterEncoding("utf-8");
          response.setContentType("multipart/form-data");
          response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
          OutputStream os = null;
          String urlPath = "http://"+url;
          try {
              URL url = new URL(urlPath);
              URLConnection urlConnection = url.openConnection();
              HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
              httpURLConnection.setRequestMethod("GET");
              httpURLConnection.setRequestProperty("Charset", "UTF-8");
              httpURLConnection.connect();
              BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
              os = response.getOutputStream();
              byte[] b = new byte[2048];
              int length;
              while ((length = bin.read(b)) > 0) {
                  os.write(b, 0, length);
              }
          }catch (Exception e){
              e.printStackTrace();
          }finally {
              try {
                  os.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
}

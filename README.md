# DownloadFromInterface
A提供了一个文件下载的接口，在调用的时候可以直接在前端用a标签来调用
<a href="http://" target="_blank">下载</a>
但是文件下载接口里并没有返回文件名，导致下载的文件千篇一律都是接口名，所以这样我就不得不考虑使用java代码调用文档下载接口，然后前端调用我的SpringMVC的Mapping，我的Java代码就作为一个中转站的作用来下载文件并且将文件名称更改为正确的名称。

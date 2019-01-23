$img_name='ngspringcloudconfig'
gradle buildOutput
docker build -t $img_name .
if (!(Test-Path .\build\dimg)) {mkdir .\build\dimg}
docker save -o build\dimg\$img_name.tar $img_name
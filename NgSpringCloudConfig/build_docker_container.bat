gradle buildOutput
docker build -t ngspringcloudconfig .
mdkir build\dimg
docker save -o ngspringcloudconfig.tar ngspringcloudconfig
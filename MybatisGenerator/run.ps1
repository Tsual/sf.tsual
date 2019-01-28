#config
$connectionURL = 'jdbc:mysql://20.26.38.75/db0'
$db_user = 'dbu'
$db_password = '123456'
$java_prefix = 'spring.mybatis'
$generator_version = '1.3.7'
$jdbc_version = '8.0.14'

Write-Host '1/4: create build.gradle'
$gradle_out = Get-Content -Encoding UTF8 .\MybatisGenerator\build.template.gradle
$gradle_out = $gradle_out.Replace('^{generator_version}', $generator_version)
$gradle_out = $gradle_out.Replace('^{jdbc_version}', $jdbc_version)

Write-Host '2/4: run gradle task'#see: .\MybatisGenerator\build.gradle
gradle copyGeneratorJars

Write-Host '3/4: build config'
if (!(Test-Path .\MybatisGenerator\out)) {mkdir .\MybatisGenerator\out}
$out_dir = Get-Item .\MybatisGenerator\out
$jdbc_file = Get-Item .\MybatisGenerator\mysql-connector-java-$jdbc_version.jar
$cfg_out = Get-Content -Encoding UTF8 .\MybatisGenerator\generatorConfig.template.xml
$cfg_out = $cfg_out.Replace('^{out_dir}', $out_dir)
$cfg_out = $cfg_out.Replace('^{java_prefix}', $java_prefix)
$cfg_out = $cfg_out.Replace('^{db_user}', $db_user)
$cfg_out = $cfg_out.Replace('^{db_password}', $db_password)
$cfg_out = $cfg_out.Replace('^{driver_jar_location}', $jdbc_file)
$cfg_out = $cfg_out.Replace('^{driver_connection_url}', $connectionURL)
Out-File -FilePath .\MybatisGenerator\generatorConfig.xml -Encoding ASCII -InputObject $cfg_out
#Write-Host 'Final GeneratorConfig:' $cfg_out

Write-Host '4/4: run jar of generatorCore'
$config_file = Get-Item .\MybatisGenerator\generatorConfig.xml
$generator_jar_file = Get-Item .\MybatisGenerator\mybatis-generator-core-$generator_version.jar
$classpath_dir = Get-Item .\MybatisGenerator
java -cp $classpath_dir -verbose:jar -jar $generator_jar_file -configfile $config_file -overwrite #see:http://www.mybatis.org/generator/index.html
#config
$connectionURL = 'jdbc:mysql://localhost/db0'
$db_user = 'dbu'
$db_password = '123456'
$java_prefix = 'spring.mybatis'
$generator_version = '1.3.7'
$jdbc_version = '8.0.13'

Write-Host '1/4: create build.gradle'
$gradle_out = Get-Content -Encoding  UTF8 $PSScriptRoot\build.template.gradle
$gradle_out = $gradle_out.Replace('^{generator_version}', $generator_version)
$gradle_out = $gradle_out.Replace('^{jdbc_version}', $jdbc_version)
Out-File -FilePath $PSScriptRoot\build.gradle -Encoding ASCII -InputObject $gradle_out
Foreach ($file in Get-ChildItem $PSScriptRoot -Filter '*.jar'){
    Remove-Item $PSScriptRoot\$file
}

Write-Host '2/4: gradle.copyGeneratorJars'#see: $PSScriptRoot\build.gradle
gradle copyGeneratorJars

Write-Host '3/4: create config'
if (Test-Path $PSScriptRoot\out) {
    Remove-Item -Recurse $PSScriptRoot\out
}
mkdir $PSScriptRoot\out
$out_dir = Get-Item $PSScriptRoot\out
$jdbc_file = Get-Item $PSScriptRoot\mysql-connector-java-$jdbc_version.jar
$cfg_out = Get-Content -Encoding ASCII $PSScriptRoot\generatorConfig.template.xml
$cfg_out = $cfg_out.Replace('^{out_dir}', $out_dir)
$cfg_out = $cfg_out.Replace('^{java_prefix}', $java_prefix)
$cfg_out = $cfg_out.Replace('^{db_user}', $db_user)
$cfg_out = $cfg_out.Replace('^{db_password}', $db_password)
$cfg_out = $cfg_out.Replace('^{driver_jar_location}', $jdbc_file)
$cfg_out = $cfg_out.Replace('^{driver_connection_url}', $connectionURL)
Out-File -FilePath $PSScriptRoot\generatorConfig.xml -Encoding ASCII -InputObject $cfg_out
#Write-Host 'Final GeneratorConfig:' $cfg_out

Write-Host '4/4: generatorCore.jar'
$config_file = Get-Item $PSScriptRoot\generatorConfig.xml
$generator_jar_file = Get-Item $PSScriptRoot\mybatis-generator-core-$generator_version.jar
$classpath_dir = Get-Item $PSScriptRoot
java -cp $classpath_dir -verbose:jar -jar $generator_jar_file -configfile $config_file -overwrite #see:http://www.mybatis.org/generator/index.html
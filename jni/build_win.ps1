$output_path='NgTsual/src/main/resources/libsf.dll'
$str = ''
$pd = 0
Foreach ($file in Get-ChildItem jni -Filter '*.cpp') {
    if ($pd.Equals(0)) {
        $pd = 1
    }
    else {
        $str += ' '
    }
    $str += ('jni/' + $file.Name)
}

Write-Output ('Source: [' + $str + ']')

gcc -shared -m64 -o $output_path -I $env:JAVA_HOME\include -I $env:JAVA_HOME\include\win32 $str
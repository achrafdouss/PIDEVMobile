<?php

$imagename=$_GET['photo'];
$imagepath=$_GET['imagepath'];
$file="C:/wamp64/www/uploadsimg/".$imagename;
file_put_contents($file,$imagepath);
copy($imagepath,"C:/wamp64/www/PIDEV/web/image/".$imagename);

?>
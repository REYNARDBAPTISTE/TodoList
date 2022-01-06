<?php
    include "connexion.php";
?>
<?php
    $idU = real_escape_string($_POST['idU']);
    $idT = real_escape_string($_POST['idT']);

    $sql = "DELETE FROM task WHERE idU ='$idU' AND idT = '$idT'";
    $CO->query($sql);
    $f = fopen('test.txt','a');
    fwrite($f,$sql);
    fclose($f);
?>
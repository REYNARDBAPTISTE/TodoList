<?php
    include "connexion.php";
?>
<?php
    $idT = $CO->real_escape_string($_POST['idT']);
    $idU = $CO->real_escape_string($_POST['idU']);

   
    
    $sql = "DELETE FROM task WHERE idU ='$idU' AND idT = '$idT'";
    $CO->query($sql);
?>
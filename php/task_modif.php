<?php
    include "connexion.php";
?>
<?php
    $idT = real_escape_string($_POST['idT']);
    $idU = real_escape_string($_POST['idU']);
    $titre = real_escape_string($_POST['titre']);
    $desc = real_escape_string($_POST['desc']);
    $tempsNew = real_escape_string($_POST['temps']);

    $sql = "UPDATE task SET titreT = '$titre', descT = '$desc', limiteT = '$tempsNew' WHERE idU = '$idU' AND idT = '$idT'";
    $CO->query($sql);
?>
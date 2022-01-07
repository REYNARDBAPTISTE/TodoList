<?php
    include "connexion.php";
?>
<?php
    $idT = $CO->real_escape_string($_POST['idT']);
    $idU = $CO->real_escape_string($_POST['idU']);
    $titre = $CO->real_escape_string($_POST['titre']);
    $desc = $CO->real_escape_string($_POST['desc']);
    $tempsNew = $CO->real_escape_string($_POST['temps']);

    $sql = "UPDATE task SET titreT = '$titre', descT = '$desc', limiteT = '$tempsNew' WHERE idU = '$idU' AND idT = '$idT'";
    $CO->query($sql);
?>
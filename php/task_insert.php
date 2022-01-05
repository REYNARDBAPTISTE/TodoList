<?php
    include "connexion.php";
?>
<?php
    $titre = htmlspecialchars($_POST['titre']);
    $desc = htmlspecialchars($_POST['desc']);
    $creation = $_POST['creation'];
    $limite = $_POST['limite'];
    $id = $_POST['id'];

    $sql = "INSERT INTO task (titreT,descT,creationT,limiteT,idU) VALUES ('$titre','$desc',$creation,$limite,$id)";
    $CO->query($sql);
?>

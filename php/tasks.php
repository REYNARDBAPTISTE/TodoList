<?php
    include "connexion.php";

    $idU = $CO->real_escape_string($_POST['idU']);
    $idU = 16;
    $sql = "SELECT * from task WHERE idU ='$idU' ORDER BY (limiteT-creationT)";
    $rs_task = $CO->query($sql) or die($CO->error . "dans la requête $sql");
    $result = array();

    foreach ($rs_task as $uneTask){
        array_push($result,array(
            "idT"=>$uneTask['idT'],
            "titreT"=>$uneTask['titreT'],
            "descT"=>$uneTask['descT'],
            "creationT"=>$uneTask['creationT'],
            "limiteT"=>$uneTask['limiteT'],
            "idU"=>$uneTask['idU']
        ));
    }
    echo json_encode($result);
?>
<form method = POST>
    <input name = idU>
</form>
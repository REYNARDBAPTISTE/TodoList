<?php
    include "connexion.php";
?>
<?php

$login = $CO->real_escape_string($_POST['login']);
$pwd = htmlspecialchars($_POST['pwd']);

$sql = "SELECT idU, emailU, loginU, nameU, first_nameU, passwordU from user WHERE loginU = '$login' OR emailU='$login'";
$rs_user = $CO->query($sql) or die($CO->error . "dans la requête $sql");

if(!$user = $rs_user->fetch_assoc())
    $message_erreur = "utilisateur inconnu";
else
    if(password_verify($pwd,$user['passwordU']))
    {
        $result = array();
        array_push($result,array(
            "idU"=>$user['idU'],
            "emailU"=>$user['emailU'],
            "loginU"=>$user['loginU'],
            "nameU"=>$user['nameU'],
            "first_nameU"=>$user['first_nameU'],
            "passwordU"=>$user['passwordU']
        ));
    }  
    else
        $message_erreur = "Mot de passe incorect";
if (!empty($message_erreur))
    echo $message_erreur;
else
    echo json_encode($result);
?>
<form method=POST>
    <input type=text name=login>
    <input type=password name=pwd>
    <button type=submit>valider</button>
</form>
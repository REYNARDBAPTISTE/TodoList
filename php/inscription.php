<?php
    include "connexion.php";
?>

<?php
    if(isset($_POST['email']))
    {
        $email = $CO->real_escape_string($_POST['email']);
        $login = $CO->real_escape_string($_POST['login']);
        $pwd = $CO->real_escape_string($_POST['pwd']);

        $message = 'Ok';

        $sql = "SELECT mail FROM membres where mail = '$email'";
        $rs = $CO->query($sql);
        $error_email = $rs->num_rows;

        $sql = "SELECT login FROM membres where login = '$login'";
        $rs = $CO->query($sql);
        $error_login = $rs->num_rows;

        if ($error_email)
            $message = "error_email";
        if ($error_login)
            $message = "error_login";
        if ($message == 'Ok')
        {
            $password = password_hash($pwd,PASSWORD_ARGON2I);
            $sql = "INSERT INTO membres (mail, login, mdp) VALUES ('$email','$login','$password')";
            $CO->query($sql);
        }
        echo $message;
    }
?>
<?php

namespace PIDEV\UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('PIDEVUserBundle:Default:index.html.twig');
    }
    public function loginAction($username,$password,Request $request)
    {
        $servername = "localhost";
        $user = "root";
        $pass = "";
        $dbname = "pidev";
// Create connection
        $conn = new \mysqli($servername, $user, $pass, $dbname);
// Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        $user = array();

        $sql = "SELECT * FROM fos_user where username='".$username."'and password='".$password."{".$username."}"."'";
       // $sql = "SELECT * FROM fos_user where username='".$username."'";

        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            // output data of each row
            while($row = $result->fetch_assoc()) {
                $row_array['id'] = $row['id'];
                $row_array['username'] = $row['username'];
                $row_array['email'] = $row['email'];
                $row_array['enabled'] = $row['enabled'];

                $row_array['password'] = $row['password'];
                $row_array['confirmation_token'] = $row['confirmation_token'];
                $row_array['nom'] = $row['nom'];
                $row_array['prenom'] = $row['prenom'];
                $row_array['addresse'] = $row['addresse'];
                $row_array['telephone'] = $row['telephone'];

                array_push($user,$row_array);
            }


        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($user);
        return new JsonResponse($formatted);
       // return $this->render('@PIDEVUser/Default/index.html.twig', array(
          //"user" => $user

        // ));
    }
}}

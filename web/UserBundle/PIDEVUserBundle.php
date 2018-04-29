<?php

namespace PIDEV\UserBundle;

use Symfony\Component\HttpKernel\Bundle\Bundle;

class PIDEVUserBundle extends Bundle
{
    public function getParent(){
        return 'FOSUserBundle';
    }
}

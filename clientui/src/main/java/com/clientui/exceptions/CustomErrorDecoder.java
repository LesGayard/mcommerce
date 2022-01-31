package com.clientui.exceptions;

import com.mproduits.web.exceptions.ProductNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response response) {

        if(response.status() == 400){
            return new ProductBadRequestException("requête incorrecte");
        }

        else if (response.status() == 404 ) {
            return new ProductNotFoundException(
                    "Produit non trouvé "
            );
        }

        return defaultErrorDecoder.decode(invoqueur, response);
    }

}

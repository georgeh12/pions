
package pions.model;

import java.io.Serializable;

/**
 *
 * @author George
 */
public class PublicKey implements Serializable {
    private String public_key = null;  //for RSA

    public PublicKey(String public_key) {
        this.public_key = public_key;
    }

    public String get(){
        return this.public_key;
    }
}

package tech.youmu.ckl.utils;

import java.util.Map;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import net.minidev.json.JSONObject;

/**
 * 
 * <p>Title:JsonWebTokenUtil</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月19日上午11:31:19</p>
 * <p>Description:数据加密</p>
 */
public class JsonWebTokenUtil
{


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月19日上午11:27:44;</p>
     *	<p>Description: 加密;</p>
     *  @param map
     *  @param secret
     *  @throws Exception
     */
    public static String encryptByJWT(Map<String,Object> map,String secret) throws Exception{
        Payload payload = new Payload(JSONObject.toJSONString(map));

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        JWSObject jwsObject = new JWSObject(header, payload);

        JWSSigner signer =  new MACSigner(secret.getBytes());
        jwsObject.sign(signer);

        return jwsObject.serialize();
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月19日上午11:28:01;</p>
     *	<p>Description: 解密;</p>
     *  @param code
     *  @param secretKey
     *  @return
     * @throws Exception 
     */
    public static JWTClaimsSet decodeByJWT(String code, String secretKey) throws Exception {
            SignedJWT jwt = SignedJWT.parse(code);
            JWTClaimsSet set = jwt.getJWTClaimsSet();
            MACVerifier mac = new MACVerifier(secretKey.getBytes());
            Boolean verify = mac.verify(jwt.getHeader(), jwt.getSigningInput(), jwt.getSignature());
            if (!verify) {
                return null;
            }
            return set;
    }
}

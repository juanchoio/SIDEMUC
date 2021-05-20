package co.com.poli.sidemuc.auth;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "alguna.clave.secreta";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAzxeM+bHn8VRHaNil6eqaMk+pqJ4kBk5mOXx9+b8+/QkAzXkD\n" +
            "dMCSs2gG8vdrhor6yoAsqj+fykqn4x1IW6Cz+jt/STHMZ7U7ghb7i+B8VHHJzbbM\n" +
            "rDVnp3e+TgmolNosB7kcoIKcj8BzAU/fv6RNzYdWfOcxale4luYkAEl55M0pUFr7\n" +
            "yFWnXe4f9SOCyk32Jk85mFUIlseIPwxx0Ov2FBrOAM8VRNsAQLYvcvJcvWl9nqAT\n" +
            "sBDesKy1f7J7ACCmaueQShcj1p1NxIaWtKuaDAiT8A6vGIRVyyC74d4Vv+43VfMf\n" +
            "BKCGtBe21m9dLND0yXMR2f2JmjUIlbvFd65x1wIDAQABAoIBAQCXl1CtssnrANto\n" +
            "5zXEHeQTsfaqJGCQcTEpIXUBrTnJdZ6tych63UBFrX9wuSZvjSp5swt+8CXiqXVt\n" +
            "rZC66c70/Dp3dMmAnMxbtMEbqg64rbagLBB0en9OujuG2lEZ7P5QBPPsls9N9vz4\n" +
            "V+ScXwRIz6nCsmWcx2uqofp2b1KX5TkhWBvzkfABu4dT5UWeKkGz+qLrko65rva7\n" +
            "V/rE3IVlVEDkEtYlpELAFfuxLgIdU/jzAqCTpLZQsaqZhKxkHFsc/5PIjJXhD6t4\n" +
            "RR1tLZCBZDjYRWQmVV030Q9oH4jZahzVojR0/nnskyosuLNYPPaaD8MuF3fhCGg6\n" +
            "Er3/qPnJAoGBAOdGihx/DBlIH3oD38N8EBUrLi6WKFuinu9j2RWCtFCvwzYIyf0J\n" +
            "MtOcYnUb3lKP/PRtDHMgV/F1m3sF9s41xKbfX0zc1Nm9gdvVLtvjW/oMPNvNsOoR\n" +
            "UI3NYC2RHCiRkhvEbNTHn13Fo/JQKhisUwoLKdJB71oXvgz+vGHXB9RrAoGBAOU7\n" +
            "KkEWRcYY3LFwomk/4zyOer9RGgjA4MioUfl6OttvDzR/b6pZmi4wBedqUT72cXh1\n" +
            "gVCqE7VpFoPhk6RrPZR1e/Qg0GC0ZZ9j4To127oRQ1e290h4B+9YgXf0imbuCoj/\n" +
            "J25KHcNZROfCy8Oj8z4TTB46BTh/qWNuroha+tNFAoGBAJizS2dLPyrGIvpF6o1A\n" +
            "scG4U/KwdA8kzTmUPHXmabXIhuR7Yxot46AyZhOahe0PYt3jzk+JMGed1j2hD3bR\n" +
            "q3jejSbLu8HUTRRfqmPGtyjewaQDKGczbXNjAbi0Udo7WzP4Oj83WnLolrnb0h+e\n" +
            "T+mWu1M285kRigs8ehdDicjnAoGAQsAz0bg2oTTkLxdpc9SSyzOCMuve9OWVj1jU\n" +
            "Zh1dvkBc4SCq16Q37XM2hOjEt9zztewht+eOzajIUacCY0KHMA/2BGT5yO8/GGbx\n" +
            "Uw4T6d7VCVnSMFwjk2LmcJPZKZfvoW0LgmY6vIPmOMbUpye60Ty1Uaf7lTQ3kyjL\n" +
            "0hEMMTECgYAnTt4RDUAY3hdg2/M1msSykZXeDRow5pHm/vjIhuszykJnjnvkPN1t\n" +
            "SefLiKuH3PFujUKoid41WiskAkLvHyFbPx8zRo3h/0ppsfty/z4cpyJzYVBqEYN5\n" +
            "RwsoP5/RI85bNa0kLgr6N9j7I/I1dEqLvuTpHR2uFEtOwCsQP/6KZQ==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzxeM+bHn8VRHaNil6eqa\n" +
            "Mk+pqJ4kBk5mOXx9+b8+/QkAzXkDdMCSs2gG8vdrhor6yoAsqj+fykqn4x1IW6Cz\n" +
            "+jt/STHMZ7U7ghb7i+B8VHHJzbbMrDVnp3e+TgmolNosB7kcoIKcj8BzAU/fv6RN\n" +
            "zYdWfOcxale4luYkAEl55M0pUFr7yFWnXe4f9SOCyk32Jk85mFUIlseIPwxx0Ov2\n" +
            "FBrOAM8VRNsAQLYvcvJcvWl9nqATsBDesKy1f7J7ACCmaueQShcj1p1NxIaWtKua\n" +
            "DAiT8A6vGIRVyyC74d4Vv+43VfMfBKCGtBe21m9dLND0yXMR2f2JmjUIlbvFd65x\n" +
            "1wIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}

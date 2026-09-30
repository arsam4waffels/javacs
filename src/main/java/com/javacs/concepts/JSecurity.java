package com.javacs.concepts;

public class JSecurity {
    static class Cryptography {
        /*
         *                        CRYPTOGRAPHY
         *                             │
         *           ┌─────────────────┼─────────────────┐
         *           │                 │                 │
         *           HASHING           SYMMETRIC         ASYMMETRIC
         *           │                 ENCRYPTION        CRYPTOGRAPHY
         *           │                 │                 │
         *           SHA-256           AES               RSA
         *           SHA-3             ChaCha20          EC
         *           │                 │                 │
         *           │                 │          ┌──────┴──────┐
         *           │                 │          │             │
         *           │                 │          Encryption    Signature
         *           │                 │
         *           │                 │
         *           │                 AEAD
         *           │                 │
         *           │                 GCM
         *           │
         *           └───────────────┐
         *                           │
         *                           MAC
         *                           │
         *                           HMAC
         * */
    }
}

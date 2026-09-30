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
         *
         *      [1] Authentication
         *      [2] Authorization
         *      [3] Certificates
         *      [4] PKI
         *      [5] TLS
         *      [6] Key Management
         *
         *    Attacker
         *    │
         *    ├── Can read database?
         *    ├── Can intercept network?
         *    ├── Can modify files?
         *    ├── Can execute code?
         *    ├── Can steal device?
         *    ├── Can access logs?
         *    └── Can access memory?
         * */
    }
}

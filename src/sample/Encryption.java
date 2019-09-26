package sample;

public class Encryption {
        final int bias = 97;//1072 for rus -отсуп в символах
        final int letters = 26;//33 for rus количество символов алфавита

        public String encrypt(String information,String key) {
            String encrypt = "";
            final int keyLen = key.length();
            for (int i = 0, len = information.length(); i < len; i++) {
                encrypt += (char) (((information.charAt(i) + key.charAt(i % keyLen) - 2 * this.bias) % this.letters) + this.bias);
            }
            return encrypt;
        }

        public String decrypt(String hidedinformation, String key) {
            String decrypt = "";
            final int keyLen = key.length();
            for (int i = 0, len = hidedinformation.length(); i < len; i++) {
                decrypt += (char) (((hidedinformation.charAt(i) - key.charAt(i % keyLen) + this.letters) % this.letters) + this.bias);
            }
            return decrypt;
        }


}
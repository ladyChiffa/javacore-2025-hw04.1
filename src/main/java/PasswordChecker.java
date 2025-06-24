public class PasswordChecker {
    private int minLength;
    private int maxRepeats;
    public void setMinLength(int len) {
        if (len < 0) {
            throw new IllegalArgumentException("Нельзя передавать значение <0, передали: " + len);
        }
        this.minLength = len;
    }

    public void setMaxRepeats(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Нельзя передавать значение <0, передали: " + count);
        }
        this.maxRepeats = count;
    }

    public boolean verify (String password) {
        if (this.minLength == 0) {
            throw new IllegalStateException("Чекеру не установлен аттрибут: minLength");
        }
        if (this.maxRepeats == 0) {
            throw new IllegalStateException("Чекеру не установлен аттрибут: maxRepeats");
        }

        if (password.length() < this.minLength) {
            return false;
        }

        int count = 0;
        int length = password.length();
        char firstChar;
        char nextChar;
        for (int i = 0; i < length; i++) {
            if (i + this.maxRepeats >= length) {
                return true;
            }

            firstChar = password.charAt(i);
            count = 1;
            for (int j = 1; j <= this.maxRepeats; j++) {
                nextChar = password.charAt(i+j);
                if (nextChar == firstChar) {
                    count++;
                }
                if (count > this.maxRepeats) {
                    return false;
                }
            }
        }
        return true;
    }
}

package Others;


class Abecedarian {

    public static boolean isAbecedarian(String s) {
        int index = s.length() - 1;

        for (int i = 0; i < index; i++) {

            if (s.charAt(i) <= s.charAt(i + 1)) {
            } //Need to check if each letter for the whole word is less than the one before it

            else {
                return false;
            }
        }
        return true;
    }
}

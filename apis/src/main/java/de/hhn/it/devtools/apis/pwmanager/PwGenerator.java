package de.hhn.it.devtools.apis.pwmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class PwGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SpecialCharacters = "!@#$%&*()_+-=[]|,./?><";
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean useSpecialCharacters;

    public PwGenerator(PasswordGeneratorBuilder builder) {
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
        this.useSpecialCharacters = builder.useSpecialCharacters;
    }

    /**
     * A builder class to manage the personal configuration.
     *
     */
    public static class PasswordGeneratorBuilder {

        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;
        private boolean useSpecialCharacters;

        public PasswordGeneratorBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
            this.useSpecialCharacters = false;
        }

        /**
         * signals the PwGeneratorBuilder to use lowercase letters.
         *
         * @param useLower should be true to use Special Characters. Default false.
         * @return the PwGeneratorBuilder itself.
         */
        public PasswordGeneratorBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        /**
         * signals the PwGeneratorBuilder to use uppercase letters.
         *
         * @param useUpper should be true to use Special Characters. Default false.
         * @return the PwGeneratorBuilder itself.
         */
        public PasswordGeneratorBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        /**
         * signals the PwGeneratorBuilder to use numbers.
         *
         * @param useDigits be true to use Special Characters. Default false.
         * @return the PwGeneratorBuilder itself.
         */
        public PasswordGeneratorBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        /**
         * signals the PwGeneratorBuilder to use Special Characters.
         *
         * @param useSpecialCharacters should be true to use Special Characters. Default false.
         * @return the PwGeneratorBuilder itself.
         */
        public PasswordGeneratorBuilder useSpecialCharacters(boolean useSpecialCharacters) {
            this.useSpecialCharacters = useSpecialCharacters;
            return this;
        }

        /**
         * Generates a PwGenerator with this builder.
         *
         * @return the newly generated PwGenerator.
         */
        public PwGenerator build() {
            return new PwGenerator(this);
        }
    }

    /**
     * This method will generate a password depending on the properties you
     * defined earlier. It will use the categories with a probability.
     *
     * @param length the length of the password you would like to generate.
     * @return a password that uses the categories you define when constructing
     * the object with a probability.
     */
    public String generate(int length) {

        return "password";
    }
}


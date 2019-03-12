package org.apache.cassandra.config;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;

public class TransparentDataEncryptionOptions
{
    public boolean enabled = false;
    public int chunk_length_kb = 64;
    public String cipher = "AES/CBC/PKCS5Padding";
    public String key_alias;
    public int iv_length = 16;

    public ParameterizedClass key_provider;

    public TransparentDataEncryptionOptions()
    {   }

    public TransparentDataEncryptionOptions(boolean enabled)
    {
        this.enabled = enabled;
    }

    public TransparentDataEncryptionOptions(String cipher, String keyAlias, ParameterizedClass keyProvider)
    {
        this(true, cipher, keyAlias, keyProvider);
    }

    public TransparentDataEncryptionOptions(boolean enabled, String cipher, String keyAlias, ParameterizedClass keyProvider)
    {
        this.enabled = enabled;
        this.cipher = cipher;
        key_alias = keyAlias;
        key_provider = keyProvider;
    }

    public String get(String key)
    {
        return key_provider.parameters.get(key);
    }

    @VisibleForTesting
    public void remove(String key)
    {
        key_provider.parameters.remove(key);
    }

    public boolean equals(Object o)
    {
        return o instanceof TransparentDataEncryptionOptions && equals((TransparentDataEncryptionOptions) o);
    }

    public boolean equals(TransparentDataEncryptionOptions other)
    {
        // not sure if this is a great equals() impl....
        return Objects.equal(cipher, other.cipher) &&
               Objects.equal(key_alias, other.key_alias);
    }
}

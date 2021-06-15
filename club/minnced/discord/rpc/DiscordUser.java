/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.sun.jna.Structure
 */
package club.minnced.discord.rpc;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordUser
extends Structure {
    private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("userId", "username", "discriminator", "avatar"));
    public String userId;
    public String username;
    public String discriminator;
    public String avatar;

    public DiscordUser(String string) {
        this.setStringEncoding(string);
    }

    public DiscordUser() {
        this("UTF-8");
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DiscordUser)) {
            return false;
        }
        DiscordUser discordUser = (DiscordUser)((Object)object);
        return Objects.equals(this.userId, discordUser.userId) && Objects.equals(this.username, discordUser.username) && Objects.equals(this.discriminator, discordUser.discriminator) && Objects.equals(this.avatar, discordUser.avatar);
    }

    public int hashCode() {
        return Objects.hash(this.userId, this.username, this.discriminator, this.avatar);
    }

    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }
}


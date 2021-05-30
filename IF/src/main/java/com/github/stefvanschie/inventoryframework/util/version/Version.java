package com.github.stefvanschie.inventoryframework.util.version;

/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * gets minecraft version from package version of the server.
 */
public final class Version {
    public static final Version CURRENT = new Version();

    /**
     * server version text.
     */
    @NotNull
    private final String rawVersion;

    /**
     * parsed server version
     */
    private final int major;
    private final int minor;
    private final int micro;

    /**
     * ctor.
     */
    private Version() {
        this.rawVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
        Pattern pattern = Pattern.compile("v?(?<major>[0-9]+)[._](?<minor>[0-9]+)(?:[._]R(?<micro>[0-9]+))?(?<sub>.*)");
        Matcher matcher = pattern.matcher(this.rawVersion);
        if (matcher.matches()) {
            this.major = Integer.parseInt(matcher.group("major"));
            this.minor = Integer.parseInt(matcher.group("minor"));
            this.micro = Integer.parseInt(matcher.group("micro"));
        } else {
            this.major = 0;
            this.minor = 0;
            this.micro = 0;
        }
    }

    /**
     * gets major part of the version.
     *
     * @return major part.
     */
    public int getMajor() {
        return this.major;
    }

    /**
     * gets micro part of the version.
     *
     * @return micro part.
     */
    public int getMicro() {
        return this.micro;
    }

    /**
     * gets minor part of the version.
     *
     * @return minor part.
     */
    public int getMinor() {
        return this.minor;
    }

    /**
     * obtains the raw version.
     *
     * @return raw version.
     */
    @NotNull
    public String getRawVersion() {
        return this.rawVersion;
    }
}

/*
 * Copyright (C) 2019, 2020 Yaroslav Pronin <proninyaroslav@mail.ru>
 *
 * This file is part of LibreTorrent.
 *
 * LibreTorrent is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LibreTorrent is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LibreTorrent.  If not, see <http://www.gnu.org/licenses/>.
 */

package app.src.main.java.org.proninyaroslav.libretorrent.core.system;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import app.src.main.java.org.proninyaroslav.libretorrent.core.exception.UnknownUriException;
import org.proninyaroslav.libretorrent.core.utils.Utils;

class FsModuleResolverImpl implements FsModuleResolver {
    private final Context appContext;
    private final SafFsModule safModule;
    private final DefaultFsModule defaultModule;

    public FsModuleResolverImpl(@NonNull Context appContext) {
        this.appContext = appContext;
        this.safModule = new SafFsModule(appContext);
        this.defaultModule = new DefaultFsModule(appContext);
    }

    @Override
    public FsModule resolveFsByUri(@NonNull Uri uri) throws UnknownUriException {
        if (Utils.isSafPath(appContext, uri)) {
            return safModule;
        } else if (Utils.isFileSystemPath(uri)) {
            return defaultModule;
        } else {
            throw new UnknownUriException("Cannot resolve file system for the given uri: " + uri);
        }
    }
}

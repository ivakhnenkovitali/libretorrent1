/*
 * Copyright (C) 2016-2021 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package app.src.main.java.org.proninyaroslav.libretorrent.core.model.session;

import android.net.Uri;

import androidx.annotation.NonNull;

import app.src.main.java.org.proninyaroslav.libretorrent.core.exception.DecodeException;
import app.src.main.java.org.proninyaroslav.libretorrent.core.exception.TorrentAlreadyExistsException;
import app.src.main.java.org.proninyaroslav.libretorrent.core.exception.UnknownUriException;
import app.src.main.java.org.proninyaroslav.libretorrent.core.logger.Logger;
import app.src.main.java.org.proninyaroslav.libretorrent.core.model.AddTorrentParams;
import app.src.main.java.org.proninyaroslav.libretorrent.core.model.TorrentEngineListener;
import org.proninyaroslav.libretorrent.core.model.data.MagnetInfo;
import org.proninyaroslav.libretorrent.core.model.data.entity.Torrent;
import app.src.main.java.org.proninyaroslav.libretorrent.core.settings.SessionSettings;

import java.io.File;
import java.io.IOException;

public interface TorrentSession
{
    Logger getLogger();

    void addListener(TorrentEngineListener listener);

    void removeListener(TorrentEngineListener listener);

    TorrentDownload getTask(String id);

    void setSettings(@NonNull SessionSettings settings);

    void setSettings(@NonNull SessionSettings settings, boolean keepPort);

    SessionSettings getSettings();

    byte[] getLoadedMagnet(String hash);

    void removeLoadedMagnet(String hash);

    Torrent addTorrent(
            @NonNull AddTorrentParams params,
            boolean removeFile
    ) throws
            IOException,
            TorrentAlreadyExistsException,
            DecodeException,
            UnknownUriException;

    void deleteTorrent(@NonNull String id, boolean withFiles);

    void restoreTorrents();

    MagnetInfo fetchMagnet(@NonNull String uri) throws Exception;

    MagnetInfo parseMagnet(@NonNull String uri);

    void cancelFetchMagnet(@NonNull String infoHash);

    long getDownloadSpeed();

    long getUploadSpeed();

    long getTotalDownload();

    long getTotalUpload();

    int getDownloadSpeedLimit();

    int getUploadSpeedLimit();

    int getListenPort();

    long getDhtNodes();

    void enableIpFilter(@NonNull Uri path);

    void disableIpFilter();

    void pauseAll();

    void resumeAll();

    void pauseAllManually();

    void resumeAllManually();

    void setMaxConnectionsPerTorrent(int connections);

    void setMaxUploadsPerTorrent(int uploads);

    void setAutoManaged(boolean autoManaged);

    boolean isDHTEnabled();

    boolean isPeXEnabled();

    void start();

    void requestStop();

    boolean isRunning();

    long dhtNodes();

    int[] getPieceSizeList();

    void download(
            @NonNull String magnetUri,
            File saveDir,
            boolean paused,
            boolean sequentialDownload
    );

    void setDefaultTrackersList(@NonNull String[] trackersList);
}

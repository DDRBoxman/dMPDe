package com.recursivepenguin.android.dMPDe;

import android.content.Context;
import android.media.AudioManager;

public class Command {

	private AudioManager mAudioManager;
	
	public Command(AudioManager amanager) {
		mAudioManager = amanager;
	}
	
	public String status() {
		
		String statusString = "";
		
		int maxVol = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int minVol = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		statusString += "volume: " + (minVol/maxVol) * 100 + "\n";
		
		return statusString;
	}
	
	//return available commands
	public String commands() {
		return "command: status\n";
	}
	
	//return outputs
	public String outputs() {
		return "outputid: 1\noutputname: Taco\noutputenabled: 1\n";
	}
	
	//return tag types
	public String tagtypes() {
		return "tagtype: title\n";
	}
/**
* The command registry.
*
* This array must be sorted!
*/
	/*
static const struct command commands[] = {
{ “add”, PERMISSION_ADD, 1, 1, handle_add },
{ “addid”, PERMISSION_ADD, 1, 2, handle_addid },
{ “clear”, PERMISSION_CONTROL, 0, 0, handle_clear },
{ “clearerror”, PERMISSION_CONTROL, 0, 0, handle_clearerror },
{ “close”, PERMISSION_NONE, -1, -1, handle_close },
{ “commands”, PERMISSION_NONE, 0, 0, handle_commands },
{ “consume”, PERMISSION_CONTROL, 1, 1, handle_consume },
{ “count”, PERMISSION_READ, 2, -1, handle_count },
{ “crossfade”, PERMISSION_CONTROL, 1, 1, handle_crossfade },
{ “currentsong”, PERMISSION_READ, 0, 0, handle_currentsong },
{ “decoders”, PERMISSION_READ, 0, 0, handle_decoders },
{ “delete”, PERMISSION_CONTROL, 1, 1, handle_delete },
{ “deleteid”, PERMISSION_CONTROL, 1, 1, handle_deleteid },
{ “disableoutput”, PERMISSION_ADMIN, 1, 1, handle_disableoutput },
{ “enableoutput”, PERMISSION_ADMIN, 1, 1, handle_enableoutput },
{ “find”, PERMISSION_READ, 2, -1, handle_find },
{ “findadd”, PERMISSION_READ, 2, -1, handle_findadd},
{ “idle”, PERMISSION_READ, 0, -1, handle_idle },
{ “kill”, PERMISSION_ADMIN, -1, -1, handle_kill },
{ “list”, PERMISSION_READ, 1, -1, handle_list },
{ “listall”, PERMISSION_READ, 0, 1, handle_listall },
{ “listallinfo”, PERMISSION_READ, 0, 1, handle_listallinfo },
{ “listplaylist”, PERMISSION_READ, 1, 1, handle_listplaylist },
{ “listplaylistinfo”, PERMISSION_READ, 1, 1, handle_listplaylistinfo },
{ “listplaylists”, PERMISSION_READ, 0, 0, handle_listplaylists },
{ “load”, PERMISSION_ADD, 1, 1, handle_load },
{ “lsinfo”, PERMISSION_READ, 0, 1, handle_lsinfo },
{ “mixrampdb”, PERMISSION_CONTROL, 1, 1, handle_mixrampdb },
{ “mixrampdelay”, PERMISSION_CONTROL, 1, 1, handle_mixrampdelay },
{ “move”, PERMISSION_CONTROL, 2, 2, handle_move },
{ “moveid”, PERMISSION_CONTROL, 2, 2, handle_moveid },
{ “next”, PERMISSION_CONTROL, 0, 0, handle_next },
{ “notcommands”, PERMISSION_NONE, 0, 0, handle_not_commands },
{ “outputs”, PERMISSION_READ, 0, 0, handle_devices },
{ “password”, PERMISSION_NONE, 1, 1, handle_password },
{ “pause”, PERMISSION_CONTROL, 0, 1, handle_pause },
{ “ping”, PERMISSION_NONE, 0, 0, handle_ping },
{ “play”, PERMISSION_CONTROL, 0, 1, handle_play },
{ “playid”, PERMISSION_CONTROL, 0, 1, handle_playid },
{ “playlist”, PERMISSION_READ, 0, 0, handle_playlist },
{ “playlistadd”, PERMISSION_CONTROL, 2, 2, handle_playlistadd },
{ “playlistclear”, PERMISSION_CONTROL, 1, 1, handle_playlistclear },
{ “playlistdelete”, PERMISSION_CONTROL, 2, 2, handle_playlistdelete },
{ “playlistfind”, PERMISSION_READ, 2, -1, handle_playlistfind },
{ “playlistid”, PERMISSION_READ, 0, 1, handle_playlistid },
{ “playlistinfo”, PERMISSION_READ, 0, 1, handle_playlistinfo },
{ “playlistmove”, PERMISSION_CONTROL, 3, 3, handle_playlistmove },
{ “playlistsearch”, PERMISSION_READ, 2, -1, handle_playlistsearch },
{ “plchanges”, PERMISSION_READ, 1, 1, handle_plchanges },
{ “plchangesposid”, PERMISSION_READ, 1, 1, handle_plchangesposid },
{ “previous”, PERMISSION_CONTROL, 0, 0, handle_previous },
{ “random”, PERMISSION_CONTROL, 1, 1, handle_random },
{ “rename”, PERMISSION_CONTROL, 2, 2, handle_rename },
{ “repeat”, PERMISSION_CONTROL, 1, 1, handle_repeat },
{ “replay_gain_mode”, PERMISSION_CONTROL, 1, 1, handle_replay_gain_mode },
{ “replay_gain_status”, PERMISSION_READ, 0, 0,
handle_replay_gain_status },
{ “rescan”, PERMISSION_ADMIN, 0, 1, handle_rescan },
{ “rm”, PERMISSION_CONTROL, 1, 1, handle_rm },
{ “save”, PERMISSION_CONTROL, 1, 1, handle_save },
{ “search”, PERMISSION_READ, 2, -1, handle_search },
{ “seek”, PERMISSION_CONTROL, 2, 2, handle_seek },
{ “seekid”, PERMISSION_CONTROL, 2, 2, handle_seekid },
{ “setvol”, PERMISSION_CONTROL, 1, 1, handle_setvol },
{ “shuffle”, PERMISSION_CONTROL, 0, 1, handle_shuffle },
{ “single”, PERMISSION_CONTROL, 1, 1, handle_single },
{ “stats”, PERMISSION_READ, 0, 0, handle_stats },
{ “status”, PERMISSION_READ, 0, 0, handle_status },
ifdef ENABLE_SQLITE
{ “sticker”, PERMISSION_ADMIN, 3, -1, handle_sticker },
endif
{ “stop”, PERMISSION_CONTROL, 0, 0, handle_stop },
{ “swap”, PERMISSION_CONTROL, 2, 2, handle_swap },
{ “swapid”, PERMISSION_CONTROL, 2, 2, handle_swapid },
{ “tagtypes”, PERMISSION_READ, 0, 0, handle_tagtypes },
{ “update”, PERMISSION_ADMIN, 0, 1, handle_update },
{ “urlhandlers”, PERMISSION_READ, 0, 0, handle_urlhandlers },
};
	*/
}

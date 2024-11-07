Simple media slideshow application written using Kotlin, Clean architecture, Compose.

1. Crossfade effect:
   Couldent find a stable solution for crossfading MediaItems/Image/ExoPlayer.
   Tried using Compose CrossFade but the behaviour was unstable and didnt work in some cases.
   If that was a show stoper i would probably seek different ways to implement this specipic screen maybe without compose -
   custom classes, catching frames from streems, xml etc. 

2. Check for updates:
   Saw the modified field in playlists response doesnt changee so didnt try given theres no way to test it.
   But if i would make an attempt i would probably use the modified timestamp as my slidshow update trigger. If the latest response
   from the backend returned a newer date i would search for a similar way to diffutil's - Myers' difference algorithm to find the
   smallest number of insertions, deletions, and moves needed to change the old list into the new one.
   From there theres two options:

   A. I would use media3 api's (if media3 supported crossfade animations):

      // Adds a media item at position 1 in the playlist.
      player.addMediaItem(/* index= */ 1, MediaItem.fromUri(thirdUri))
      // Moves the third media item from position 2 to the start of the playlist.
      player.moveMediaItem(/* currentIndex= */ 2, /* newIndex= */ 0)
      // Removes the first item from the playlist.
      player.removeMediaItem(/* index= */ 0)
      // Replace the second item in the playlist.
      player.replaceMediaItem(/* index= */ 1, MediaItem.fromUri(newUri))

   B. I would use a Mutable List with SnapshotStateList: This type of list supports dynamic updates and triggers recompositions
      in Compose when items are added, removed, or changed. 
      Additionally i would manage the Current Index: Instead of resetting the index, making sure the index continues from its current
      position unless it exceeds the bounds of the updated list (for example, if items are removed).



   
     

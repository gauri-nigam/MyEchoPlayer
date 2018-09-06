package com.internshala.echo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Gauri Nigam on 12/23/2017.
 */
class Songs(var songID: Long, var songTitle: String, var songArtist: String,var songData: String, var dateAdded: Long): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
      //  p0.writeLong(songID)
      //  p0.writeString(songTitle)
     //   p0.writeString(songArtist)
      //  p0.writeString(songData)
      //  p0.writeLong(dateAdded)
    }

    override fun describeContents(): Int {
        return 0
    }

    object Statified {
        val nameComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.songTitle.toUpperCase()
            val songTwo = song2.songTitle.toUpperCase()
            songOne.compareTo(songTwo)

        }
        var dateComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.dateAdded.toDouble()
            val songTwo = song2.dateAdded.toDouble()
            songTwo.compareTo(songOne)
        }

    }//encapsulation for fetching song details from phone

    companion object CREATOR : Parcelable.Creator<Songs> {
        override fun createFromParcel(parcel: Parcel): Songs {
            return Songs(parcel)
        }

        override fun newArray(size: Int): Array<Songs?> {
            return arrayOfNulls(size)
        }
    }
}

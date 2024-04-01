package domain

import data.RawPhotoItem


/**
 * https://www.flickr.com/services/api/misc.urls.html
 *
 * You can construct the source URL to a photo once you know its ID, server ID, and secret as returned by many API methods.

The URL takes the following format: https://live.staticflickr.com/{server-id}/{id}_{secret}.jpg
 */
data class DomainPhotoItem(
    val rawPhotoItem: RawPhotoItem,
    val url: String = with(rawPhotoItem) {
        "https://live.staticflickr.com/$server/${id}_$secret.jpg"
    }
)
package ro.dobrescuandrei.timelineviewv2.model

import android.content.res.Resources
import org.joda.time.DateTime
import ro.dobrescuandrei.timelineviewv2.TimelineView
import ro.dobrescuandrei.timelineviewv2.base.BaseTimelineRecyclerViewAdapter
import java.io.Serializable

abstract class DateTimeInterval
(
    val fromDateTime : DateTime,
    val toDateTime : DateTime
) : Serializable
{
    abstract fun getPreviousDateTimeInterval() : DateTimeInterval?
    abstract fun getNextDateTimeInterval() : DateTimeInterval?
    abstract fun getShiftedDateTimeInterval(amount : Int) : DateTimeInterval?

    abstract operator fun minus(another : DateTimeInterval) : Int

    fun contains(dateTime : DateTime) = dateTime in fromDateTime..toDateTime

    abstract fun toRecyclerViewAdapter(timelineView : TimelineView) : BaseTimelineRecyclerViewAdapter<*>

    override fun toString() = "${this::class.java.simpleName} $fromDateTime - $toDateTime"
    abstract fun toString(resources : Resources) : String

    override fun equals(other : Any?) =
        (other as? DateTimeInterval)?.fromDateTime==fromDateTime&&
        (other as? DateTimeInterval)?.toDateTime==toDateTime

    override fun hashCode() = fromDateTime.millis.toInt()
}

package robo.abstracts

import org.javacord.api.entity.permission.PermissionType
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.event.message.MessageDeleteEvent
import org.javacord.api.event.message.MessageEditEvent
import org.javacord.api.event.message.reaction.ReactionAddEvent
import org.javacord.api.event.message.reaction.ReactionRemoveEvent
import org.javacord.api.event.server.member.ServerMemberJoinEvent
import org.javacord.api.event.server.member.ServerMemberLeaveEvent
import org.javacord.api.event.user.UserChangeActivityEvent
import org.javacord.api.event.user.UserChangeNameEvent
import org.javacord.api.event.user.UserChangeNicknameEvent
import org.javacord.api.event.user.UserChangeStatusEvent
import robo.models.Param

import java.util.*
import kotlin.collections.HashSet


abstract class Kit(private var title: String) {

    private var query: String = ""
    private var brief: String = ""
    private var guide: String = ""
    private val requiredPermissions: MutableSet<PermissionType> = HashSet()
    private var subKits: MutableSet<Kit> = HashSet()

    fun getSubKits(): MutableSet<Kit> = subKits

    fun getQuery(): String { return query }

    fun getTitle(): String {
        return title
    }

    fun getBrief(): String {
        return brief
    }

    fun getGuide(): String {
        return guide
    }

    protected fun setTitle(title: String): Kit {
        this.title = title
        return this
    }

    protected fun setQuery(query: String): Kit {
        this.query = query
        return this
    }

    protected fun setBrief(brief: String): Kit {
        this.brief = brief
        return this
    }

    protected fun setGuide(guide: String): Kit {
        this.guide = guide
        return this
    }

    protected fun setRequiredPermissions(vararg requiredPermissions: PermissionType) {
        this.requiredPermissions.addAll(requiredPermissions)
    }

    protected fun installKits(vararg subKits: Kit) {
        this.subKits.addAll(Arrays.asList(*subKits))
    }

    fun onStart() {}
    fun onPulse() {}
    fun onQuery(params: List<Param>) {}
    fun onEvent(event: ServerMemberJoinEvent) {}
    fun onEvent(event: ServerMemberLeaveEvent) {}
    fun onEvent(event: UserChangeNicknameEvent) {}
    fun onEvent(event: UserChangeActivityEvent) {}
    fun onEvent(event: UserChangeStatusEvent) {}
    fun onEvent(event: UserChangeNameEvent) {}
    fun onEvent(event: ReactionRemoveEvent) {}
    fun onEvent(event: MessageDeleteEvent) {}
    fun onEvent(event: MessageCreateEvent) {}
    fun onEvent(event: MessageEditEvent) {}
    fun onEvent(event: ReactionAddEvent) {}

}

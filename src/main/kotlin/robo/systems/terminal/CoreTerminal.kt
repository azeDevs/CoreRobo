package robo.systems.terminal

import robo.abstracts.Form
import robo.models.posts.KitPost
import robo.models.posts.KitText
import robo.models.posts.Mess
import robo.systems.ResIcons
import robo.systems.data.CoreData
import robo.systems.output.CoreOutput
import robo.systems.output.Status
import robo.systems.pulse.Pulse
import robo.utils.Az

import javax.inject.Inject
import javax.inject.Singleton
import java.time.LocalTime
import java.time.format.DateTimeFormatter

import robo.systems.ResCoreTerminal.*


/**
 * - configures [KitText] objects
 * - manages Logs hopper for bulk posting to [CoreOutput]
 * - manages server coreTerminal settings via [CoreData]
 */
@Singleton
class CoreTerminal @Inject
constructor(private val output: CoreOutput) : Pulse {
    private val debugMask: Int
    private val adminMask: Int

    init {
        this.adminMask = 0
        this.debugMask = 0
    }

    override fun onPulse(current: Long) {
        Log.removeAll().forEach { serverID, logs ->
            logs.forEach { log ->
                if (log.mask <= adminMask) output.queuePost(KitPost(CHAN_LBOX, log.text))
                if (log.mask <= debugMask) Az.prnt(log.text.toFormlessString() + "\n")
            }
        }
    }

    fun logCoreOnline() {
        output.setCurrentStatus(Status(1, "debug"))
        output.queuePost(KitPost(CHAN_LBOX, KitText(String.format(FORM_START, "robo-mk0"))))
    }

    fun log(mess: Mess) {
        if (mess.channelID == CHAN_LBOX) return
        val time = LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm:ss a"))
        val type = if (mess.isUser) ResIcons.IC_USER else ResIcons.IC_BOT
        Log.add(
            mess.serverID, 0, KitText().form(Form.MD)
                .add(FORM_AUTHOR, type, Az.table(-4, mess.user.name))
                .add(FORM_CHANNEL, Az.table(-4, mess.channelAsString))
                .add(FORM_SERVER, Az.table(-6, mess.serverAsString), time)
                .form(Form.CODE).add(mess.text).add("\n")
        )
    }

    companion object {

        private val CHAN_TEST = 445640530072698890L
        private val CHAN_LBOX = 391351078106693652L
    }
}

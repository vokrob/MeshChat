package io.github.vokrob.meshchat.core.common.di

import javax.inject.Qualifier

/**
 * Диспетчер для операций ввода-вывода: обращения к базе данных, файлам, транспорту.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

/**
 * Диспетчер для вычислительной работы, не блокирующей поток UI.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

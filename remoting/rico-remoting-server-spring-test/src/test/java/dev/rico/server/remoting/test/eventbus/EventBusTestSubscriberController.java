/*
 * Copyright 2018-2019 Karakun AG.
 * Copyright 2015-2018 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.rico.server.remoting.test.eventbus;

import dev.rico.remoting.server.RemotingController;
import dev.rico.remoting.server.RemotingModel;
import dev.rico.remoting.server.event.RemotingEventBus;
import dev.rico.remoting.server.event.MessageEvent;
import dev.rico.remoting.server.event.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@RemotingController(EventBusTestConstants.EVENT_BUS_SUBSCIBER_CONTROLLER_NAME)
public class EventBusTestSubscriberController {

    @RemotingModel
    private EventBusTestModel model;

    @Autowired
    private RemotingEventBus eventBus;

    @PostConstruct
    public void init() {
        eventBus.subscribe(EventBusTestConstants.TEST_TOPIC, new MessageListener<>() {
            @Override
            public void onMessage(MessageEvent<String> message) {
                model.valueProperty().set(message.getData());
            }
        });
    }
}

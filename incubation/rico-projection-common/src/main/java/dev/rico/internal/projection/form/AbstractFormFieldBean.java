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
package dev.rico.internal.projection.form;

import dev.rico.internal.projection.base.AbstractProjectableBean;
import dev.rico.internal.projection.base.Icon;
import dev.rico.internal.projection.message.Message;
import dev.rico.internal.projection.metadata.KeyValue;
import dev.rico.remoting.ObservableList;
import dev.rico.remoting.Property;
import dev.rico.remoting.RemotingBean;

@RemotingBean
public abstract class AbstractFormFieldBean<T> extends AbstractProjectableBean implements FormField<T> {

    private Property<Boolean> mandatory;

    private Property<Boolean> disabled;

    private Property<Boolean> editable;

    private ObservableList<Message> messages;

    private Property<String> description;

    private Property<Icon> icon;

    private ObservableList<KeyValue> layoutMetadata;

    private Property<String> title;


    @Override
    public Property<Boolean> mandatoryProperty() {
        return mandatory;
    }

    @Override
    public Property<Boolean> disabledProperty() {
        return disabled;
    }

    @Override
    public Property<Boolean> editableProperty() {
        return editable;
    }

    @Override
    public ObservableList<Message> getMessages() {
        return messages;
    }

    @Override
    public Property<String> descriptionProperty() {
        return description;
    }

    @Override
    public Property<Icon> iconProperty() {
        return icon;
    }

    @Override
    public ObservableList<KeyValue> getLayoutMetadata() {
        return layoutMetadata;
    }

    @Override
    public Property<String> titleProperty() {
        return title;
    }
}

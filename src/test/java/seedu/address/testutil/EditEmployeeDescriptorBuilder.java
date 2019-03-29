package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.GitHubAccount;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.skill.Skill;

/**
 * A utility class to help with building EditEmployeeDescriptor objects.
 */
public class EditEmployeeDescriptorBuilder {

    private EditCommand.EditEmployeeDescriptor descriptor;

    public EditEmployeeDescriptorBuilder() {
        descriptor = new EditCommand.EditEmployeeDescriptor();
    }

    public EditEmployeeDescriptorBuilder(EditCommand.EditEmployeeDescriptor descriptor) {
        this.descriptor = new EditCommand.EditEmployeeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEmployeeDescriptor} with fields containing {@code employee}'s details
     */
    public EditEmployeeDescriptorBuilder(Employee employee) {
        descriptor = new EditCommand.EditEmployeeDescriptor();
        descriptor.setName(employee.getName());
        descriptor.setPhone(employee.getPhone());
        descriptor.setEmail(employee.getEmail());
        descriptor.setGitHubAccount(employee.getGithub());
        descriptor.setSkills(employee.getSkills());
    }

    /**
     * Sets the {@code Name} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code GitHubAccount} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withGithubAccount(String account) {
        descriptor.setGitHubAccount(new GitHubAccount(account));
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code EditEmployeeDescriptor}
     * that we are building.
     */
    public EditEmployeeDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.setSkills(skillSet);
        return this;
    }

    public EditCommand.EditEmployeeDescriptor build() {
        return descriptor;
    }
}

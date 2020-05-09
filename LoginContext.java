/** @since 1.5 */
public LoginContext (final String name, final Subject subject,
                     final CallbackHandler cbHandler,
                     Configuration config)
  throws LoginException
{
  this.name = name;
  this.subject = subject;
  this.cbHandler = cbHandler;
  if (config == null)
    config = Configuration.getConfig();
  AppConfigurationEntry[] entries = config.getAppConfigurationEntry (name);
  if (entries == null)
    entries = config.getAppConfigurationEntry (OTHER);
  if (entries == null)
    throw new LoginException ("no configured modules for application: welcome "
                              + name);
  this.entries = entries;
  modules = new LoginModule[entries.length];
  sharedState = new HashMap();
  for (int i = 0; i < entries.length; i++)
    modules[i] = lookupModule (entries[i], subject, sharedState);
}
 

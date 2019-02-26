accountHolder = ['id': request.body.getID(),
                 'daysDelinquent': request.body.getDaysDelinquent(),
                 'balance': request.body.getBALANCE(),
                 'mismatch': request.body.getMismatch(),
                 'bar': request.body.getBAR(),
                 'risk_score': request.body.getRisk_score(),
                 'skip_ID': request.body.getSkip_ID(),
                 'aflag': request.body.getAflag(),
                 'nflag': request.body.getNflag(),
                 'eflag': request.body.getEflag(),
                 'rflag': request.body.getRflag(),
                 'sflag': request.body.getSflag(),
                 'bad_Number': request.body.getBad_Number(),
                 'daflag': request.body.getDAFlag(),
                 'list_1': request.body.getLIST_1()]

helper = ['rand1': Math.random(),
          'rand2': Math.random()]

['lookup': 'default-stateless-ksession',
 'commands': [
   ['insert': ['object': ['com.toyota.tfs.decisionservice.AccountHolder': accountHolder],
               'out-identifier': 'account',
               'return-object': true]],
   ['insert': ['object': ['com.toyota.tfs.decisionservice.Helper': helper],
               'return-object': false]],
   ['fire-all-rules': ['out-identifier': 'firedActivations']],
   ['dispose': [:]]
 ]
]